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


 export function initPlayAudio(options) {
  return AudioGphone.initPlayAudio(options);
}

export function writePlayAudio(base64){
  return AudioGphone.writePlayAudio(base64);
}




