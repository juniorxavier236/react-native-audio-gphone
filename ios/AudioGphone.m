#import <React/RCTBridgeModule.h>

@interface RCT_EXTERN_MODULE(AudioGphone, NSObject)


RCT_EXPORT_METHOD(initPlay:(NSDictionary *) options)


RCT_EXTERN_METHOD(multiply:(float)a withB:(float)b
                 withResolver:(RCTPromiseResolveBlock)resolve
                 withRejecter:(RCTPromiseRejectBlock)reject)

+ (BOOL)requiresMainQueueSetup
{
  return NO;
}

@end