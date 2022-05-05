import 'package:flutter/material.dart';
import 'package:flutter/services.dart';


class NativeHome extends StatefulWidget {
  @override
  _NativeHomeState createState() => _NativeHomeState();
}

class _NativeHomeState extends State<NativeHome> {
  static const platform = MethodChannel('testSDK/test');
  // Get battery level.

  // Get battery level.
  String _batteryLevel = 'Message not fetched';

  Future<void> _getBatteryLevel() async {
    String batteryLevel;
    try {
      final int result = await platform.invokeMethod('methodName');
      batteryLevel = 'Battery level at $result % .';
    } on PlatformException catch (e) {
      batteryLevel = "Failed to get battery level: '${e.message}'.";
    }

    setState(() {
      _batteryLevel = batteryLevel;
    });
  }

  @override
  Widget build(BuildContext context) {
    return Material(
      child: Center(
        child: Column(
          mainAxisAlignment: MainAxisAlignment.spaceEvenly,
          children: [
            ElevatedButton(
              child: const Text('Get Battery Level'),
              onPressed: _getBatteryLevel,
            ),
            Text(_batteryLevel),
          ],
        ),
      ),
    );
  }
}