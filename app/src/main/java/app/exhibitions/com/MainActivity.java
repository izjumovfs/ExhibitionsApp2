package app.exhibitions.com;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.FrameLayout;

import app.exhibitions.com.fileexhibitsloader.FileExhibitsLoader;
import app.exhibitions.com.fragments.ExhibitionsFragment;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ExhibitionsFragment fragment = ExhibitionsFragment.newInstance();
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, fragment, "EXHIBITIONS_FRAGMENT").commitAllowingStateLoss();
    }
}
