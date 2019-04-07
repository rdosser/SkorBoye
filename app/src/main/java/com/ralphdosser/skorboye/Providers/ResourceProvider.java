package com.ralphdosser.skorboye.Providers;

import android.media.MediaPlayer;

import com.ralphdosser.skorboye.R;
import com.ralphdosser.skorboye.main.App;

public class ResourceProvider {

    private static ResourceProvider iResourceProvider;

    private MediaPlayer mediaPlayerClickMinus;
    private MediaPlayer mediaPlayerClickPlus;
    private MediaPlayer mediaPlayerFail;

    public static final float LEFT_VOLUME = 1.0f;
    public static final float RIGHT_VOLUME = 1.0f;

    public static ResourceProvider getInstance() {
        if (iResourceProvider == null) {
            synchronized (ResourceProvider.class) {
                if (iResourceProvider == null) {
                    iResourceProvider = new ResourceProvider();

                    MediaPlayer mediaPlayerClickMinus = MediaPlayer.create(App.getContext(), R.raw.click_minus);
                    mediaPlayerClickMinus.setVolume(LEFT_VOLUME, RIGHT_VOLUME);
                    iResourceProvider.setMediaPlayerClickMinus(mediaPlayerClickMinus);

                    MediaPlayer mediaPlayerClickPlus = MediaPlayer.create(App.getContext(), R.raw.click_plus);
                    mediaPlayerClickPlus.setVolume(LEFT_VOLUME, RIGHT_VOLUME);
                    iResourceProvider.setMediaPlayerClickPlus(mediaPlayerClickPlus);

                    MediaPlayer mediaPlayerClickFail = MediaPlayer.create(App.getContext(), R.raw.fail);
                    mediaPlayerClickFail.setVolume(LEFT_VOLUME, RIGHT_VOLUME);
                    iResourceProvider.setMediaPlayerFail(mediaPlayerClickFail);
                }
            }
        }
        return iResourceProvider;
    }

    public MediaPlayer getMediaPlayerClickMinus() {
        return mediaPlayerClickMinus;
    }

    public void setMediaPlayerClickMinus(MediaPlayer mediaPlayerClickMinus) {
        this.mediaPlayerClickMinus = mediaPlayerClickMinus;
    }

    public MediaPlayer getMediaPlayerClickPlus() {
        return mediaPlayerClickPlus;
    }

    public void setMediaPlayerClickPlus(MediaPlayer mediaPlayerClickPlus) {
        this.mediaPlayerClickPlus = mediaPlayerClickPlus;
    }

    public MediaPlayer getMediaPlayerFail() {
        return mediaPlayerFail;
    }

    public void setMediaPlayerFail(MediaPlayer mediaPlayerFail) {
        this.mediaPlayerFail = mediaPlayerFail;
    }

    public void playClickPlus() {
        mediaPlayerClickPlus.start();
    }

    public void playClickMinus() {
        mediaPlayerClickMinus.start();
    }

    public void playFail() {
        mediaPlayerFail.start();
    }

}
