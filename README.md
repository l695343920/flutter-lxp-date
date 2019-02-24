# flutter_lxp_date

A Get Time Flutter plugin.
因为flutter里面不能获取本周本季度本月本年的时间，所以想着自己开发个插件给大家使用，目前Android可以使用，后期开发Ios(等我买了苹果电脑在说！！！),本人QQ695343920,可以一起向着技术方向前进。

pub #
![][当前图片路径(https://ctfive.oss-cn-hangzhou.aliyuncs.com/Student/2019/02/24/0521db125c044fb7ba96566349bbada10163.png)]

Usage #
To use this plugin, add flutterlxpdate as a dependency in your pubspec.yaml file.

Example #

import 'package:flutter/material.dart';
import 'dart:async';

import 'package:flutter/services.dart';
import 'package:flutter_lxp_date/flutter_lxp_date.dart';

void main() => runApp(MyApp());

class MyApp extends StatefulWidget {
  @override
  _MyAppState createState() => _MyAppState();
}

class _MyAppState extends State<MyApp> {
  String _platformTime = 'Unknown';

  @override
  void initState() {
    super.initState();
    initPlatformState();
  }

  // Platform messages are asynchronous, so we initialize in an async method.
  Future<void> initPlatformState() async {
    String platformTime;
    // Platform messages may fail, so we use a try/catch PlatformException.
    try {
      platformTime = await FlutterLxpDate.platformTime;
    } on PlatformException {
      platformTime = 'Failed to get platform time.';
    }

    // If the widget was removed from the tree while the asynchronous platform
    // message was in flight, we want to discard the reply rather than calling
    // setState to update our non-existent appearance.
    if (!mounted) return;

    setState(() {
      _platformTime = platformTime;
    });
  }

  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      home: Scaffold(
        appBar: AppBar(
          title: const Text('Plugin example app'),
        ),
        body: Center(
          child: Text('Running on: $_platformTime\n'),
        ),
      ),
    );
  }
}
