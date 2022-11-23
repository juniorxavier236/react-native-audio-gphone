#import <React/RCTBridgeModule.h>

@interface RCT_EXTERN_MODULE(AudioGphone, NSObject)

 RCT_EXTERN_METHOD(initPlay:(NSString))
 
 RCT_EXTERN_METHOD(startPlay:(RCTPromiseResolveBlock)resolve
                  rejecter:(RCTPromiseRejectBlock)reject);

 RCT_EXTERN_METHOD(stopPlay:(RCTPromiseResolveBlock)resolve
                  rejecter:(RCTPromiseRejectBlock)reject);

 RCT_EXTERN_METHOD(setVolumePlay:(float)volume 
                  resolve:(RCTPromiseResolveBlock) resolve
                  rejecter:(RCTPromiseRejectBlock) reject);

 RCT_EXTERN_METHOD(writePlay:(NSString))


+ (BOOL)requiresMainQueueSetup
{
  return NO;
}





@end


// https://teabreak.e-spres-oh.com/swift-in-react-native-the-ultimate-guide-part-1-modules-9bb8d054db03