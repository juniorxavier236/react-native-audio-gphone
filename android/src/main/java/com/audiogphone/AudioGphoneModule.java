package com.audiogphone;

import androidx.annotation.NonNull;

import com.facebook.react.bridge.Promise;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.module.annotations.ReactModule;

import com.facebook.react.bridge.ReadableMap;

import java.lang.Math;


import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;
import java.nio.FloatBuffer;

import com.facebook.react.bridge.Callback;

import android.media.AudioRecord;
import android.media.AudioTrack;
import android.media.AudioManager;
import android.media.AudioFormat;
import android.os.Build;
import android.util.Base64;
import android.util.Log;

import android.content.Context;

@ReactModule(name = AudioGphoneModule.NAME)
public class AudioGphoneModule extends ReactContextBaseJavaModule {
  public static final String NAME = "AudioGphone";

  private AudioManager audioManager;

  private static AudioTrack audioTrack;

  private static AudioTrack audioPlay;
  private boolean isFloat = false;
  private int bufferSizeTrack = 2048;


  public AudioGphoneModule(ReactApplicationContext reactContext) {
    super(reactContext);
    audioManager = ((AudioManager) reactContext.getSystemService(Context.AUDIO_SERVICE));

    if (audioPlay != null) {
      audioPlay.stop();
      audioPlay.release();
      audioPlay = null;
  }
  }

  @Override
  @NonNull
  public String getName() {
    return NAME;
  }

  @ReactMethod
  public void initPlay(final ReadableMap options) {
      Thread playThread = new Thread(new Runnable() {
          public void run() {
              //int streamType = AudioManager.STREAM_MUSIC;
               int streamType = AudioManager.STREAM_VOICE_CALL;
              //int streamType = AudioManager.STREAM_SYSTEM;
           

            

              int sampleRateInHz = 8000;
              int channelConfig = AudioFormat.CHANNEL_OUT_MONO;
              int audioFormat = AudioFormat.ENCODING_PCM_16BIT;
               int mode = AudioTrack.MODE_STREAM;
              //int mode = AudioTrack.MODE_STATIC
            //   if (options.hasKey("streamType")) {
            //       streamType = options.getInt("streamType");
            //   }
              ;
              // if (options.hasKey("bitsPerChannel")) {
              // int bitsPerChannel = options.getInt("bitsPerChannel");
              // isFloat = false;

              // if (bitsPerChannel == 8) {
              // audioFormat = AudioFormat.ENCODING_PCM_8BIT;
              // } else if (bitsPerChannel == 32) {
              // audioFormat = AudioFormat.ENCODING_PCM_FLOAT;
              // isFloat = true;
              // }
              // }
              if (options.hasKey("channelsPerFrame")) {
                  int channelsPerFrame = options.getInt("channelsPerFrame");

                  // every other case --> CHANNEL_IN_MONO
                  if (channelsPerFrame == 2) {
                      channelConfig = AudioFormat.CHANNEL_OUT_STEREO;
                  }
              }
              if (options.hasKey("sampleRate")) {
                  sampleRateInHz = options.getInt("sampleRate");
              }
              // if (options.hasKey("bufferSize")) {
              // bufferSize = options.getInt("bufferSize");
              // }
              // if (isFloat) {
              // bufferSizeTrack = AudioRecord.getMinBufferSize(8000,
              // AudioFormat.CHANNEL_IN_MONO, AudioFormat.ENCODING_PCM_16BIT);
              // Log.d("recorder", "setting buffer size " + bufferSize);
              // }
              audioPlay = new AudioTrack(streamType, sampleRateInHz, channelConfig, audioFormat, bufferSizeTrack,
                      mode);
              audioPlay.play();

              if (audioManager != null) {

                

                audioManager.setMode(AudioManager.MODE_IN_COMMUNICATION);
                //audioManager.setStreamVolume(AudioManager.STREAM_VOICE_CALL, audioManager.getStreamMaxVolume(AudioManager.STREAM_VOICE_CALL), 0);
                audioManager.setSpeakerphoneOn(true);
              }


          }
      });

      playThread.start();
  }

  @ReactMethod
  public void startPlay() {
      if (audioPlay != null) {
          audioPlay.play();
      }
  }

  @ReactMethod
  public void stopPlay() {
      if (audioPlay != null) {
          audioPlay.stop();
          audioPlay.release();
          audioPlay = null;
      }
  }

  @ReactMethod
  public void pausePlay() {
      if (audioPlay != null) {
          audioPlay.pause();
      }
  }

  @ReactMethod
  public void setVolumePlay(float gain) {
      if (audioPlay != null) {
          audioPlay.setVolume(gain);
      }
  }


  @ReactMethod
  public void setSpeakerphoneOn(final boolean enable) {

    //   if (enable != manager.isSpeakerphoneOn())  {
          if (audioManager != null) {
            audioManager.setSpeakerphoneOn(enable);
          }
    //   }
  }

  // @ReactMethod
  // public void setSpeakerphoneon(final boolean enable) {
  //     if (enable != audioManager.isSpeakerphoneOn())  {
  //         audioManager.setSpeakerphoneOn(enable);
  //     }
  // }



  // @ReactMethod
  // public void write(String base64String) {
  // byte[] bytesArray = Base64.decode(base64String, Base64.NO_WRAP);
  // if (audioTrack != null && bytesArray != null) {
  // if (isFloat && Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {

  // FloatBuffer fb = ByteBuffer.wrap(bytesArray).asFloatBuffer();
  // float[] buffer = new float[fb.capacity()];
  // ByteBuffer.wrap(bytesArray).order(ByteOrder.nativeOrder()).asFloatBuffer().get(buffer);
  // try {
  // audioTrack.write(buffer, 0, buffer.length, AudioTrack.WRITE_BLOCKING);
  // } catch (Exception ignored) {
  // }
  // } else {
  // short[] buffer = byte2short(bytesArray);
  // try {
  // audioTrack.write(buffer, 0, bufferSize);
  // } catch (Exception ignored) {
  // }
  // }
  // }
  // }

  @ReactMethod
  public void writePlay(String base64String) {
      byte[] bytesArray = Base64.decode(base64String, Base64.NO_WRAP);
      // if (audioTrack != null) {
      // if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {

      // FloatBuffer fb = ByteBuffer.wrap(bytesArray).asFloatBuffer();
      // float[] buffer = new float[fb.capacity()];
      // ByteBuffer.wrap(bytesArray).order(ByteOrder.nativeOrder()).asFloatBuffer().get(buffer);
      // try {
      // audioTrack.write(buffer, 0, buffer.length, AudioTrack.WRITE_BLOCKING);
      // } catch (Exception ignored) {
      // }
      // } else {
      // short[] buffer = byte2short(bytesArray);
      // try {
      // audioTrack.write(buffer, 0, bufferSize);
      // } catch (Exception ignored) {
      // }
      // }
      try {
          short[] buffer = byte2short(bytesArray);
          // audioTrack.write(buffer, 0, bufferSizeTrack);
          audioPlay.write(buffer, 0, buffer.length);

      } catch (Exception ignored) {
          Log.d("write", "erro");
      }
      // }
  }

  public static short[] byte2short(byte[] paramArrayOfbyte) {
      short[] arrayOfShort = new short[paramArrayOfbyte.length / 2];
      for (int i = 0;; i += 2) {
          if (i >= paramArrayOfbyte.length)
              return arrayOfShort;
          byte b1 = paramArrayOfbyte[i];
          byte b2 = paramArrayOfbyte[i + 1];
          short s = (short) ((short) ((short) b1 & 0xFF) + (short) (b2 << 8));
          arrayOfShort[i / 2] = (short) s;
      }
  }
}
