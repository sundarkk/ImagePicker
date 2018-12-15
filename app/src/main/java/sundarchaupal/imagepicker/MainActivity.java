package sundarchaupal.imagepicker;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {
    ImageView imageView;
    Button piker;
   Uri uri;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        piker.findViewById(R.id.piker);
        imageView.findViewById(R.id.imageview);
        piker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //to get the content from mobile local
                Intent intent=new Intent(Intent.ACTION_GET_CONTENT);
                //to call the gallery images from the mobile
                intent.setType("image/*");
                //to request the image from gallery
                startActivityForResult(intent,1);
            }
        });
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(Intent.ACTION_SEND);
                intent.setType("image/*");
                intent.putExtra(Intent.EXTRA_STREAM,uri);
                startActivity(Intent.createChooser(intent,"share"));


            }
        });
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==1&&requestCode==RESULT_OK&&data!=null)
        {
            //to request the address from data and pass to uri
            uri=data.getData();
            //to store the data in bitmap
            try {
                Bitmap bitmap=(Bitmap) MediaStore.Images.Media.getBitmap(getContentResolver(),uri);
                //to show the image  into imageview using bitmap
                imageView.setImageBitmap(bitmap);
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }
}
