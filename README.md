# flutter_lxp_date

A Get Time Flutter plugin.

因为flutter里面不能获取本周本季度本月本年的时间，所以想着自己开发个插件给大家使用。

<img src='https://ctfive.oss-cn-hangzhou.aliyuncs.com/Student/2019/02/24/0521db125c044fb7ba96566349bbada10163.png'>

Example #

{

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

  Future<void> initPlatformState() async {

    String platformTime;

    try {

      platformTime = await FlutterLxpDate.platformTime;

    } on PlatformException {

      platformTime = 'Failed to get platform time.';

    }
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

}
