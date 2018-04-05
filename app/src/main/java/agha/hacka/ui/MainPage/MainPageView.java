package agha.hacka.ui.MainPage;

import java.util.List;

import agha.hacka.pogo.FacultyResponse;


// This interface should be implemented by the activity.
public interface MainPageView {

    public void onSuccess(List<FacultyResponse> facultyResponse);
    public void onFail();
}
