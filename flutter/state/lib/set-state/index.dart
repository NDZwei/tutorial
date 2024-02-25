
import 'package:flutter/material.dart';
import 'package:state/set-state/my_buttons.dart';

class SetStateIndex extends StatefulWidget {
  const SetStateIndex({super.key});

  @override
  State<SetStateIndex> createState() => _SetStateIndexState();
}

class _SetStateIndexState extends State<SetStateIndex> {
  String topic = "Flutter";

  callback(varTopic) {
    setState(() {
      topic = varTopic;
    });
  }

  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      debugShowCheckedModeBanner: false,
      home: Scaffold(
        appBar: AppBar(
          title: const Text("Pass data between classes"),
          centerTitle: true,
        ),
        body: Column(
          children: [
            Container(
              width: double.maxFinite,
              height: 70,
              margin: const EdgeInsets.only(top: 50, left: 20, right: 20),
              decoration: BoxDecoration(
                color: Colors.blue,
                borderRadius: BorderRadius.circular(20)
              ),
              child: Center(
                  child: Text(
                    "We are learning: " + topic,
                    style: const TextStyle(
                      fontSize: 20,
                      color: Colors.white
                    ),
                  )
              ),
            ),
            MyButton(topic: "River pod", callbackFunction: callback,),
            MyButton(topic: "BloC", callbackFunction: callback),
            MyButton(topic: "GetX", callbackFunction: callback),
          ],
        ),
      ),
    );
  }
}
