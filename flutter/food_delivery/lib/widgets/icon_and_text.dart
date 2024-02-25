
import 'package:flutter/material.dart';
import 'package:food_delivery/widgets/small_text.dart';

class IconAndText extends StatelessWidget {
  final IconData icon;
  final String text;
  final Color iconColor;
  final double iconSize;

  const IconAndText({
    required this.icon,
    required this.text,
    required this.iconColor,
    this.iconSize = 24,
    super.key});

  @override
  Widget build(BuildContext context) {
    return Row(
      children: [
        Icon(icon, color: iconColor, size: iconSize,),
        const SizedBox(width: 5,),
        SmallText(text: text, color: const Color(0xFFccc7c5),)
      ],
    );
  }
}
