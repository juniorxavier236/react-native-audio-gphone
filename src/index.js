import { NativeModules, Platform } from 'react-native';

const LINKING_ERROR =
  `The package 'react-native-audio-gphone' doesn't seem to be linked. Make sure: \n\n` +
  Platform.select({ ios: "- You have run 'pod install'\n", default: '' }) +
  '- You rebuilt the app after installing the package\n' +
  '- You are not using Expo Go\n';

const AudioGphone = NativeModules.AudioGphone
  ? NativeModules.AudioGphone
  : new Proxy(
      {},
      {
        get() {
          throw new Error(LINKING_ERROR);
        },
      }
    );




const Audio = {};

Audio.initPlay = options => AudioGphone.initPlay(options);
Audio.startPlay = () => AudioGphone.startPlay();
Audio.stopPlay = () => AudioGphone.stopPlay();
Audio.writePlay = base64 => AudioGphone.writePlay(base64);
Audio.setVolumePlay = options => AudioGphone.setVolumePlay(options);
if(Platform.OS === 'android'){
  Audio.setSpeakerphoneOn = options => AudioGphone.setSpeakerphoneOn(options);
}




export default Audio;

