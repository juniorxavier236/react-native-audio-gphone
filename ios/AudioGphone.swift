import AVFoundation


@objc(AudioGphone)
class AudioGphone: NSObject {

    var engine: AVAudioEngine!
    var player: AVAudioPlayerNode!
    

  @objc(multiply:withB:withResolver:withRejecter:)
  func multiply(a: Float, b: Float, resolve:RCTPromiseResolveBlock,reject:RCTPromiseRejectBlock) -> Void {
    resolve(a*b)
  }


    // @objc
    // func initPlay(options: NSDictionary)->Bool {
    //     // TODO: Setup
    //     //engine = AVAudioEngine()
    //     player = AVAudioPlayerNode()
    //     try? AVAudioSession.sharedInstance().setCategory(.playback)

    //     player.play()

    //     return true
    // }

}
