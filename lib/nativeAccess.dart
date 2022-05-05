import 'package:flutter/material.dart';
import 'package:flutter/services.dart';

class NativeHome extends StatefulWidget {
  @override
  _NativeHomeState createState() => _NativeHomeState();
}

class _NativeHomeState extends State<NativeHome> {
  static const platform = MethodChannel('comminicationNative/Flutter');
  // Get battery level.

  // Get battery level.
  String responseStatus = 'Message not fetched';

  Future<void> _getResponse() async {
    String reponse;
    try {
      final String result = await platform.invokeMethod('callPARAVISION');
      reponse = 'Response is $result';
    } on PlatformException catch (e) {
      reponse = "Failed to get response '${e.message}'.";
    }

    setState(() {
      responseStatus = reponse;
    });
  }

  @override
  Widget build(BuildContext context) {
    return Material(
      child: SingleChildScrollView(
        child: Center(
          child: Column(
            mainAxisAlignment: MainAxisAlignment.spaceEvenly,
            children: [
              ElevatedButton(
                child: const Text('Invoke Paravision sdk'),
                onPressed: _getResponse,
              ),
              Text(responseStatus),
            ],
          ),
        ),
      ),
    );
  }
}
