package com.example.flutterlxpdate;

import io.flutter.plugin.common.MethodCall;
import io.flutter.plugin.common.MethodChannel;
import io.flutter.plugin.common.MethodChannel.MethodCallHandler;
import io.flutter.plugin.common.MethodChannel.Result;
import io.flutter.plugin.common.PluginRegistry.Registrar;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import org.json.JSONException;
import org.json.JSONObject;

/** FlutterLxpDatePlugin */
public class FlutterLxpDatePlugin implements MethodCallHandler {
  SimpleDateFormat format = new SimpleDateFormat("yyyy-M-d");
  Calendar c = Calendar.getInstance();
  int y, m;
  /** Plugin registration. */
  public static void registerWith(Registrar registrar) {
    final MethodChannel channel = new MethodChannel(registrar.messenger(), "flutter_lxp_date");
    channel.setMethodCallHandler(new FlutterLxpDatePlugin());
  }

  @Override
  public void onMethodCall(MethodCall call, Result result) {
    if (call.method.equals("getPlatformTime")) {
      y = c.get(Calendar.YEAR);
      m = c.get(Calendar.MONTH) + 1;

      JSONObject jsonObject = new JSONObject();//转成json发送给flutter端
      try {
        jsonObject.put("today", getToday());
        jsonObject.put("beginWeek", getWeekStart());
        jsonObject.put("endWeek", getWeekEnd());
        jsonObject.put("benginMonth", getMonthStart());
        jsonObject.put("endMonth", getMonthEnd());
        jsonObject.put("beginQuarter", getQuarterStart());
        jsonObject.put("endQuarter", getQuarterEnd());
        jsonObject.put("beginYear", getYearBegin());
        jsonObject.put("endYear", getYearEnd());
      } catch (JSONException e) {
        e.printStackTrace();
      }

      result.success(jsonObject.toString());
    } else {
      result.notImplemented();
    }
  }

  public String getToday() {// 本日

    c = Calendar.getInstance();
    return format.format(c.getTime());
  }

  public String getWeekStart() {// 本周开始
    c.setFirstDayOfWeek(Calendar.SUNDAY);
    c.set(Calendar.HOUR_OF_DAY, 0);
    c.set(Calendar.MINUTE, 0);
    c.set(Calendar.SECOND, 0);
    c.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);
    return format.format(c.getTime());
  }

  public String getWeekEnd() {// 本周结束
    c.setFirstDayOfWeek(Calendar.SUNDAY);
    c.set(Calendar.HOUR_OF_DAY, 23);
    c.set(Calendar.MINUTE, 59);
    c.set(Calendar.SECOND, 59);
    c.set(Calendar.DAY_OF_WEEK, Calendar.SATURDAY);
    return format.format(c.getTime());
  }

  public String getMonthStart() {// 本月开始
    c = Calendar.getInstance();
    c.add(Calendar.MONTH, 0);
    c.set(Calendar.DAY_OF_MONTH, 1);// 设置为1号,当前日期既为本月第一天
    return format.format(c.getTime());
  }

  public String getMonthEnd() {// 本月结束
    c = Calendar.getInstance();
    c.set(Calendar.DAY_OF_MONTH, c.getActualMaximum(Calendar.DAY_OF_MONTH));
    return format.format(c.getTime());
  }

  public String getQuarterStart() {// 本季度开始
    String startTime;
    if (m <= 3) {
      startTime = y + "-1-1";

    } else if (m <= 6) {
      startTime = y + "-4-1";

    } else if (m <= 9) {
      startTime = y + "-7-1";

    } else {
      startTime = y + "-10-1";

    }
    return startTime;
  }

  public String getQuarterEnd() {// 本季度结束
    String endTime;
    if (m <= 3) {

      endTime = y + "-3-31";
    } else if (m <= 6) {

      endTime = y + "-6-30";
    } else if (m <= 9) {

      endTime = y + "-9-30";
    } else {

      endTime = y + "-12-31";
    }
    return endTime;
  }

  public String getYearBegin() {// 本年开始

    return y + "-1-1";
  }

  public String getYearEnd() {// 本年结束

    return y + "-12-31";
  }
}
