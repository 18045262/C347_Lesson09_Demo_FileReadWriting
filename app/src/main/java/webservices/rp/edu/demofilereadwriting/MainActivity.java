package webservices.rp.edu.demofilereadwriting;

import androidx.appcompat.app.AppCompatActivity;

import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileFilter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class MainActivity extends AppCompatActivity {
    Button btnWrite, btnRead;
    TextView tv;
    String folderLocation;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnRead = findViewById(R.id.btnRead);
        btnWrite = findViewById(R.id.btnWrite);
        tv = findViewById(R.id.tv);

        folderLocation = getFilesDir().getAbsolutePath() + "/MyFolder";

        final File targetFile = new File(folderLocation, "data.txt");
        if (targetFile.exists() == false){
            boolean result = targetFile.mkdir();
            if (result == true){
                Log.d("File Read/Write", "Folder created");
            }
        }

        btnWrite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FileWriter  writer = null;
                try {
                    writer = new FileWriter(targetFile,true);
                    writer.write("Hello World!" + "\n");
                    writer.flush();
                    writer.close();
                    Toast.makeText(MainActivity.this,"Success to write!", Toast.LENGTH_LONG).show();

                } catch (IOException e) {
                    Toast.makeText(MainActivity.this,"Failed to write!", Toast.LENGTH_LONG).show();
                    e.printStackTrace();

                }

            }
        });

        btnRead.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                if(targetFile.exists() == true){
                    String data = "";

                    try {
                        FileReader reader = new FileReader(targetFile);
                        BufferedReader br = new BufferedReader(reader);

                        String line = br.readLine();
                        while (line!= null) {
                            data += line + "\n";
                            line = br.readLine();

                        }
                        br.close();
                        reader.close();
                        tv.setText(data);

                        Toast.makeText(MainActivity.this,"success to read!" + data, Toast.LENGTH_LONG).show();
                    } catch (FileNotFoundException e) {
                        Toast.makeText(MainActivity.this,"Failed to read!", Toast.LENGTH_LONG).show();
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
    }
}
