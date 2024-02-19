
import 'package:flutter/material.dart';

class StateLess extends StatelessWidget {
  final bool loading;

  const StateLess({Key? key, required this.loading}) : super(key: key);

  @override
  Widget build(BuildContext context) {
    return loading ? const CircularProgressIndicator() : const Text('Loading...');
  }
}
