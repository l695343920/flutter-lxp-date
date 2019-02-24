import 'dart:async';

import 'package:flutter/services.dart';

class FlutterLxpDate {
  static const MethodChannel _channel =
      const MethodChannel('flutter_lxp_date');

  static Future<String> get platformTime async {
    final String Time = await _channel.invokeMethod('getPlatformTime');
    return Time;
  }
}
