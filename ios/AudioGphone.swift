import AVFoundation


@objc(AudioGphone)
class AudioGphone: NSObject {


 @objc(initPlayAudio:)
  func initPlayAudio(data: String) -> Void {
  
  }

  @objc(writePlayAudio:)
  func writePlayAudio(base64: String) -> Void {

    let payload = Data(base64Encoded: base64, options: .ignoreUnknownCharacters)!


  }



}
