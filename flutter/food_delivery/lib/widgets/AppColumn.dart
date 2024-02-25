
import 'package:flutter/material.dart';
import 'package:food_delivery/widgets/small_text.dart';

import '../utils/colors.dart';
import 'big_text.dart';
import 'icon_and_text.dart';

class AppColumn extends StatelessWidget {
  final String text;
  const AppColumn({super.key, required this.text});

  @override
  Widget build(BuildContext context) {
    return Column(
      crossAxisAlignment: CrossAxisAlignment.start,
      children: [
        BigText(text: text),
        const SizedBox(
          height: 15,
        ),
        Row(
          children: [
            Wrap(
              children: List.generate(
                  5,
                      (index) => Icon(
                    Icons.star,
                    color: AppColor.mainColor,
                    size: 15,
                  )),
            ),
            const SizedBox(
              width: 15,
            ),
            SmallText(text: "5.0"),
            const SizedBox(width: 20),
            SmallText(text: "209 comments")
          ],
        ),
        const SizedBox(
          height: 15,
        ),
        Row(
          children: [
            IconAndText(
              icon: Icons.circle_sharp,
              text: "Normal",
              iconColor: AppColor.yellowColor,
            ),
            const SizedBox(
              width: 20,
            ),
            IconAndText(
              icon: Icons.location_on,
              text: "2.9km",
              iconColor: AppColor.mainColor,
            ),
            const SizedBox(
              width: 20,
            ),
            IconAndText(
              icon: Icons.access_time_rounded,
              text: "29 minute",
              iconColor: AppColor.iconColor2,
            ),
          ],
        )
      ],
    );
  }
}
