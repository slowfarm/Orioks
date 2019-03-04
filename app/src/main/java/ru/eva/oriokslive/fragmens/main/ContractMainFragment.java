package ru.eva.oriokslive.fragmens.main;

import java.util.List;

import ru.eva.oriokslive.interfaces.OnDisciplinesRecieved;
import ru.eva.oriokslive.interfaces.OnTokenRecieved;
import ru.eva.oriokslive.models.orioks.Discipline;

class ContractMainFragment {
    interface View {

        void setRecyclerView(List<Discipline> disciplineList);

        void addRecyclerVIewItems(List<Discipline> disciplineList);

        void showToast(String text);

        void unsetRefreshing();

        void finishActivity();
    }

    interface Presenter {

        void getDisciplineList();

        void setDisciplineList();
    }

    interface Repository {

        List<Discipline> getDisciplineList();

        void setDisciplineList(OnDisciplinesRecieved onDisciplinesRecieved);

        void setDisciplineList(List<Discipline> disciplineList);

        void deleteToken(OnTokenRecieved onTokenRecieved);

        void clearAllTables();
    }
}
