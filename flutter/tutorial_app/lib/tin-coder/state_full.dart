

import 'package:flutter/material.dart';

class StateFull extends StatefulWidget {
  final bool loading;
  const StateFull({super.key, required this.loading});

  @override
  State<StateFull> createState() {
    return _StateFullState();
  }
}

class _StateFullState extends State<StateFull> {
  late bool _localLoading;

  @override
  void initState() {
    _localLoading = widget.loading;
  }

  @override
  Widget build(BuildContext context) {
    return _localLoading? FloatingActionButton(onPressed: onPressed, child: const Text('Click'),) : const CircularProgressIndicator();
  }

  void onPressed() {
    setState(() {
      _localLoading = false;
    });
  }
}
