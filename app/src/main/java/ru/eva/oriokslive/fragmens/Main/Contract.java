package ru.eva.oriokslive.fragmens.Main;

import java.util.List;

import ru.eva.oriokslive.interfaces.OnDisciplinesRecieved;
import ru.eva.oriokslive.models.orioks.Disciplines;

class Contract {
    interface View {

        void setRecyclerView(List<Disciplines> disciplineList);

        void addRecyclerVIewItems(List<Disciplines> disciplinesList);

        void showToast(String text);

        void unsetRefreshing();
    }

    interface Presenter {

        void getDisciplineList();

        void setDisciplineList();
    }

    interface Repository {

        List<Disciplines> getDisciplineList();

        void setDisciplineList(OnDisciplinesRecieved onDisciplinesRecieved);
        void setDisciplineList(List<Disciplines> disciplinesList);
    }
}
