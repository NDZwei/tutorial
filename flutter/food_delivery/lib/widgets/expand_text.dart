
import 'package:flutter/material.dart';
import 'package:food_delivery/utils/colors.dart';
import 'package:food_delivery/widgets/small_text.dart';

class ExpandText extends StatefulWidget {
  final String text;
  const ExpandText({super.key, required this.text});

  @override
  State<ExpandText> createState() => _ExpandTextState();
}

class _ExpandTextState extends State<ExpandText> {
  late String firstHalf;
  late String secondHalf;
  bool hiddenText = true;
  double textHeight = 400;


  @override
  void initState() {
    super.initState();
    if(widget.text.length > textHeight) {
      firstHalf = widget.text.substring(0, textHeight.toInt());
      secondHalf = widget.text.substring(textHeight.toInt()+1, widget.text.length);
    } else {
      firstHalf = widget.text;
      secondHalf = "";
    }
  }

  @override
  Widget build(BuildContext context) {
    return Container(
      child: secondHalf.isEmpty ? SmallText(text: firstHalf, size: 16, height: 1.5,) : Column(
        children: [
          SmallText(text: hiddenText ? (firstHalf + "...") : (firstHalf+secondHalf), size: 16, height: 1.5),
          InkWell(
            onTap: () {
              setState(() {
                hiddenText = !hiddenText;
              });
            },
            child: Row(
              children: [
                SmallText(text: hiddenText ? "Xem thêm" : "Ẩn bớt", color: AppColor.mainColor, size: 16, height: 1.5),
                Icon(hiddenText ? Icons.arrow_drop_down : Icons.arrow_drop_up, color: AppColor.mainColor,)
              ],
            ),
          )
        ],
      ),
    );
  }
}
