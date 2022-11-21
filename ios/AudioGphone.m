#import <React/RCTBridgeModule.h>

@interface RCT_EXTERN_MODULE(AudioGphone, NSObject)

RCT_EXTERN_METHOD(initPlayAudio:(NSString))


RCT_EXTERN_METHOD(writePlayAudio:(NSString))


+ (BOOL)requiresMainQueueSetup
{
  return NO;
}

@end
