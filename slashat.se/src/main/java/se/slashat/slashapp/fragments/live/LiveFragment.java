package se.slashat.slashapp.fragments.live;

import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.MediaController;
import android.widget.RelativeLayout;
import android.widget.VideoView;

import se.slashat.slashapp.Callback;
import se.slashat.slashapp.R;
import se.slashat.slashapp.live.BambuserController;

/**
 * Created by nicklas on 6/18/13.
 */
public class LiveFragment extends Fragment {
    private VideoView videoView;
    private LinearLayout.LayoutParams paramsNotFullscreen;
    private boolean livestreamStarted;

    //@TODO: Handle rotation.
    //@TODO: Continue play when switching fragments.
    //@TODO: Start after pressing a playbutton.
    //@TODO: Better Mediacontroller.


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.live_fragment, null);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if (!livestreamStarted) {
            videoView = (VideoView) view.findViewById(R.id.livevideoview);

            BambuserController bambuserController = new BambuserController();

            // Call the bambuser service and retrive the URL for the stream and
            // start the videoview with this URL.
            bambuserController.startStream("3570928", new Callback<String>() {

                @Override
                public void call(String result) {
                    MediaController mediaController = new MediaController(getActivity());
                    videoView.setMediaController(mediaController);
                    videoView.setVideoPath(result);
                    videoView.requestFocus();
                    videoView.start();
                    livestreamStarted = true;
                }
            });
        }
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);

        /*if (newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE) {
            paramsNotFullscreen = (LinearLayout.LayoutParams) videoView.getLayoutParams();
            RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(paramsNotFullscreen);
            params.setMargins(0, 0, 0, 0);
            params.height = ViewGroup.LayoutParams.MATCH_PARENT;
            params.width = ViewGroup.LayoutParams.MATCH_PARENT;
            params.addRule(RelativeLayout.CENTER_IN_PARENT);
            videoView.setLayoutParams(params);

        } else if (newConfig.orientation == Configuration.ORIENTATION_PORTRAIT) {
            videoView.setLayoutParams(paramsNotFullscreen);
        }*/
    }
}
