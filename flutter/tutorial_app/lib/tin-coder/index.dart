import 'package:flutter/material.dart';
import 'package:tutorial_app/tin-coder/state_full.dart';
import 'package:tutorial_app/tin-coder/widget/column.dart';
import 'package:tutorial_app/tin-coder/widget/container.dart';
import 'package:tutorial_app/tin-coder/widget/elevated-button.dart';
import 'package:tutorial_app/tin-coder/widget/expanded.dart';
import 'package:tutorial_app/tin-coder/widget/flexible.dart';
import 'package:tutorial_app/tin-coder/widget/padding-margin.dart';
import 'package:tutorial_app/tin-coder/widget/row.dart';
import 'package:tutorial_app/tin-coder/widget/sized-box.dart';
import 'package:tutorial_app/tin-coder/widget/stack.dart';
import 'package:tutorial_app/tin-coder/widget/text-button.dart';
import 'package:tutorial_app/tin-coder/widget/text-widget.dart';

import 'state_less.dart';

class IndexTinCoder extends StatelessWidget {
  const IndexTinCoder({super.key});

  @override
  Widget build(BuildContext context) {
    return const MaterialApp(
      debugShowCheckedModeBanner: false,
      home: SafeArea(
        child: Scaffold(
          body: MyFlexible(),
        ),
      ),
    );
  }
}
