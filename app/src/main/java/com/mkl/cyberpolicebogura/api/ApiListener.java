package com.mkl.cyberpolicebogura.api;


import com.mkl.cyberpolicebogura.model.Complaints;
import com.mkl.cyberpolicebogura.model.LoginResponse;
import com.mkl.cyberpolicebogura.model.Police;
import com.mkl.cyberpolicebogura.model.Status;
import com.mkl.cyberpolicebogura.model.StatusPath;

import java.util.List;

public class ApiListener {
    public interface ComplaintsDownloadListener{
        void onComplaintsDownloadSuccess(List<Complaints> list );
        void onComplaintsDownloadFailed(String msg );
    }
    public interface PoliceListDownloadListener{
        void onPoliceListDownloadSuccess(List<Police> list );
        void onPoliceListDownloadFailed(String msg );
    }
    public interface ComplaintsSendListener{
        void onComplaintsSendSuccess(Status status);
        void onComplaintsSendFailed(String msg );
    }
    public interface DistrictSpinnerListener{
        void onDistrictClicked(int position);
    }
    public interface TypeSpinnerListener{
        void onTypeClicked(int position);
    }
    public interface SubjectSpinnerListener{
        void onSubjectClicked(int position);
    }
    public interface CheckMobileListener{
        void onMobileCheckSuccess(Status status);
        void onMobileCheckFailed(String msg );
    }

    public interface CheckIMEIListener{
        void onIMEICheckSuccess(Status status);
        void onIMEICheckFailed(String msg );
    }
    public interface UserCreateListener{
        void onUserCreateSuccess(Status status);
        void onUserCreateFailed(String msg );
    }

    public interface LoginUserListener{
        void onUserLoginSuccess(LoginResponse status);
        void onUserLoginFailed(String msg );
    }
    public interface SeenInfoChangeListener{
        void onSeenInfoChangeSuccess(Status status);
        void onSeenInfoChangeFailed(String msg );
    }
    public interface photoUpdateListener{
        void onPhotoUpdateSuccess(StatusPath status);
        void onPhotoUpdateFailed(String msg );
    }
}
