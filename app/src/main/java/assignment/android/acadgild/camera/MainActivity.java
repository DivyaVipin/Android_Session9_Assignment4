package assignment.android.acadgild.camera;

import android.Manifest;
import android.content.pm.PackageManager;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
Button btnCheck,btnRequest;
    //Permision code that will be checked in the method onRequestPermissionsResult
    private int CAMERA_CODE = 23;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnCheck=(Button)findViewById(R.id.btnCheck);
        btnRequest=(Button)findViewById(R.id.btnRequest);
        btnRequest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                requestPermission();
                if (requestPermission())
                {
                    Toast.makeText(MainActivity.this, "Request granted ", Toast.LENGTH_LONG).show();
                }

            }
        });
        btnCheck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity.this, "Checking permission for Camera", Toast.LENGTH_LONG).show();
                checkPermission();//Returns boolean
                if(!checkPermission())
                {
                    Toast.makeText(MainActivity.this, "Permission not granted,Please request for permission ", Toast.LENGTH_LONG).show();
                }

            }
        });
    }
    private boolean requestPermission()

    {

        ActivityCompat.requestPermissions(MainActivity.this,
                new String[]{Manifest.permission.CAMERA},   //request specific permission from user
                CAMERA_CODE);

        return true;
    }
    private boolean checkPermission()
    {
        int result = ActivityCompat.checkSelfPermission(MainActivity.this, Manifest.permission.CAMERA);


        return result == PackageManager.PERMISSION_GRANTED;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if(requestCode == CAMERA_CODE){

            //If permission is granted
            if(grantResults.length >0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){

                //Displaying a toast
                Toast.makeText(this,"Permission granted now you can use Camera",Toast.LENGTH_LONG).show();
            }else{
                //Displaying another toast if permission is not granted
                Toast.makeText(this,"Oops you just denied the permission",Toast.LENGTH_LONG).show();
            }
        }

    }
}
