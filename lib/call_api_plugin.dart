import 'package:flutter/services.dart';

class CallApiPlugin {
  static const platform = MethodChannel('flutter.native/helper');
  Future<bool?> getAllDataPlugin() async {
    bool? result = await platform.invokeMethod<bool>('api');

    return result;
  }
}
