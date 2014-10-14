package catchgrampoc.android.digicorp.com.catchgrampoc;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.File;


public class ImageProcessingActivity extends Activity {

    private static final String TAG = ImageProcessingActivity.class.getSimpleName();

    private Uri mImageUri;

    ImageView imgSource;
    ImageView imgPreview;
    TextView txtSourceImage;
    TextView txtPreviewImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_processing);

        imgSource = (ImageView) findViewById(R.id.imgSource);
        imgPreview = (ImageView) findViewById(R.id.imgPreview);
        txtSourceImage = (TextView) findViewById(R.id.txtSourceImage);
        txtPreviewImage = (TextView) findViewById(R.id.txtPreviewImage);

        if(getIntent().getData() != null) {
            this.mImageUri = getIntent().getData();

            imgSource.setImageURI(this.mImageUri);
            imgPreview.setImageURI(this.mImageUri);

            imgSource.postDelayed(new Runnable() {
                @Override
                public void run() {
                    String imageDimension = String.format("(%d x %d)", imgSource.getWidth(), imgSource.getHeight());
                    txtSourceImage.setText(getString(R.string.source_image) + imageDimension);
                }
            }, 2000);

            imgPreview.postDelayed(new Runnable() {
                @Override
                public void run() {
                    String imageDimension = String.format("(%d x %d)", imgPreview.getWidth(), imgPreview.getHeight());
                    txtPreviewImage.setText(getString(R.string.preview_image) + imageDimension);
                }
            }, 2000);
        } else {
            finish();
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.image_processing, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void generateAndAttachCodeClick(View v) {

    }
}
