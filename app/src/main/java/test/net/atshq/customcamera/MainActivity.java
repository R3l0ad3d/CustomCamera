package test.net.atshq.customcamera;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {           //https://www.youtube.com/watch?v=oPu42I0HSi4

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    protected void onStart() {
        super.onStart();
        Utility.checkReadStoragePermission(this);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode){

            case Utility.MY_PERMISSIONS_REQUEST_READ_EXTERNAL_STORAGE:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Utility.checkCameraPermission(MainActivity.this);
                } else {
                    Utility.checkReadStoragePermission(MainActivity.this);
                }
                break;
            case Utility.MY_PERMISSIONS_REQUEST_CAMERA:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    startActivity(new Intent(this,CameraActivity.class));
                }else {
                    Utility.checkCameraPermission(MainActivity.this);
                }
        }
    }

    public void cameraActivityClick(View view) {
        startActivity(new Intent(this,CameraActivity.class));
    }
}
