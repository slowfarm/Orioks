package ru.eva.oriokslive.service

import android.content.Context
import androidx.hilt.work.HiltWorker
import androidx.work.Constraints
import androidx.work.CoroutineWorker
import androidx.work.ExistingPeriodicWorkPolicy.UPDATE
import androidx.work.NetworkType
import androidx.work.PeriodicWorkRequestBuilder
import androidx.work.WorkManager
import androidx.work.WorkerParameters
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject
import ru.eva.oriokslive.domain.repository.DomainRepository
import ru.eva.oriokslive.network.repository.RemoteRepository
import ru.eva.oriokslive.utils.createGradeUpdateForegroundInfo
import ru.eva.oriokslive.utils.showDebtUpdatedNotification
import ru.eva.oriokslive.utils.showDisciplinesUpdatedNotification
import timber.log.Timber
import java.util.concurrent.TimeUnit

@HiltWorker
class GradeUpdateWorker @AssistedInject constructor(
    @Assisted private val context: Context,
    @Assisted private val workerParams: WorkerParameters,
    private val domainRepository: DomainRepository,
    private val remoteRepository: RemoteRepository,
) :
    CoroutineWorker(context, workerParams) {

    override suspend fun doWork(): Result {
        Timber.d("$WORKER_NAME doWork")
        setForegroundAsync(createGradeUpdateForegroundInfo(context))
        if (domainRepository.getAccessToken() != null) {
            checkDisciplines()
            checkDebts()
        }

        return Result.success()
    }

    private suspend fun checkDisciplines() {
        val remoteDisciplines = remoteRepository.getDisciplines()
        val localDisciplines = domainRepository.getDisciplines()
        if (remoteDisciplines != localDisciplines) {
            domainRepository.setDisciplines(remoteDisciplines)
            val diff = remoteDisciplines.filter { localDisciplines?.contains(it)?.not() ?: true }
            val text = diff.joinToString("\n") { "${it.name} -> ${it.currentGrade}" }
            showDisciplinesUpdatedNotification(context, text)
        }
    }

    private suspend fun checkDebts() {
        val remoteDebts = remoteRepository.getDebts()
        val localDebts = domainRepository.getDebts()
        if (remoteDebts != localDebts) {
            domainRepository.setDebts(remoteDebts)
            val diff = remoteDebts.filter { localDebts?.contains(it)?.not() ?: true }
            val text = diff.joinToString("\n") { "${it.name} -> ${it.currentScore}" }
            showDebtUpdatedNotification(context, text)
        }
    }

    companion object {
        private const val WORKER_NAME = "GradeUpdateWorker"

        @JvmStatic
        fun enqueue(context: Context) {
            Timber.d("$WORKER_NAME enqueue")
            val constraints = Constraints.Builder()
                .setRequiredNetworkType(NetworkType.CONNECTED)
                .build()
            val periodic = PeriodicWorkRequestBuilder<GradeUpdateWorker>(1, TimeUnit.HOURS)
                .addTag(WORKER_NAME)
                .setConstraints(constraints)
                .build()

            WorkManager.getInstance(context)
                .enqueueUniquePeriodicWork(WORKER_NAME, UPDATE, periodic)
        }
    }
}
