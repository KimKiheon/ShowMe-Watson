import 'package:flutter/material.dart';
import 'package:prac2/prac1.dart';
import 'package:prac2/prac2.dart';
import 'package:prac2/prac3.dart';
import 'package:prac2/prac4.dart';
// import 'package:prac2/prac5.dart';
import 'package:prac2/prac6.dart';
import 'package:prac2/prac7.dart';
import 'package:prac2/prac8.dart';
import 'package:prac2/prac9.dart';
import 'package:prac2/prac10.dart';
import 'package:prac2/prac11.dart';
import 'package:prac2/prac12.dart';
import 'package:prac2/prac13.dart';


void main() => runApp(MyApp());

class MyApp extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      initialRoute: '/',
      routes: {
        '/' : (context) => prac1(),
        '/a': (context) => prac2(),
        '/b': (context) => prac3(),
        '/c': (context) => prac4(),
        // '/d': (context) => prac5(),
        '/e': (context) => prac6(),
        '/f': (context) => prac7(),
        '/g': (context) => prac8(),
        '/h': (context) => prac9(),
        '/i': (context) => prac10(),
        '/j': (context) => prac11(),
        '/k': (context) => prac12(),
        '/l': (context) => prac13(),



      },
    );
  }
}
