import 'package:dots_indicator/dots_indicator.dart';
import 'package:flutter/material.dart';
import 'package:food_delivery/utils/colors.dart';
import 'package:food_delivery/widgets/big_text.dart';
import 'package:food_delivery/widgets/small_text.dart';

import '../widgets/icon_and_text.dart';

class FoodPageBody extends StatefulWidget {
  const FoodPageBody({super.key});

  @override
  State<FoodPageBody> createState() => _FoodPageBodyState();
}

class _FoodPageBodyState extends State<FoodPageBody> {
  PageController pageController = PageController(viewportFraction: 0.85);
  var _currentPageValue = 0.0;
  @override
  void initState() {
    super.initState();
    pageController.addListener(() {
      setState(() {
        _currentPageValue = pageController.page!;
      });
    });
  }

  @override
  Widget build(BuildContext context) {
    return Column(
      children: [
        Container(
          height: 320,
          child: PageView.builder(
            controller: pageController,
            itemCount: 5,
            itemBuilder: (context, position) {
              return _buildPageItem(position);
            }),
        ),
      new DotsIndicator(
        dotsCount: 5,
        position: _currentPageValue,
        decorator: DotsDecorator(
          activeColor: AppColor.mainColor,
          size: const Size.square(9.0),
          activeSize: const Size(18.0, 9.0),
          activeShape: RoundedRectangleBorder(
              borderRadius: BorderRadius.circular(5.0)
          ),
        ),
      )
      ],
    );
  }

  Widget _buildPageItem(int index) {
    return Stack(
      children: [
        Container(
          height: 220,
          margin: const EdgeInsets.only(left: 5, right: 10),
          decoration: BoxDecoration(
              borderRadius: BorderRadius.circular(20),
              color: index.isEven ? Colors.red : Colors.blue,
              image: const DecorationImage(
                  fit: BoxFit.cover,
                  image: AssetImage("assets/images/food0.png"))),
        ),
        Align(
          alignment: Alignment.bottomCenter,
          child: Container(
            height: 140,
            margin: const EdgeInsets.only(left: 30, right: 30, bottom: 30),
            decoration: BoxDecoration(
                borderRadius: BorderRadius.circular(20),
                color: Colors.white,
                boxShadow: const [
                  BoxShadow(
                    color: Color(0xFFe8e8e8),
                    blurRadius: 5.0,
                    offset: Offset(0, 5)
                  )
                ]
            ),
            child: Container(
              padding: const EdgeInsets.only(top: 20, left: 20, right: 20),
              child: Column(
                crossAxisAlignment: CrossAxisAlignment.start,
                children: [
                  BigText(text: "Bánh đa trộn"),
                  const SizedBox(height: 15,),
                  Row(
                    children: [
                      Wrap(
                        children: List.generate(5, (index) => Icon(Icons.star, color: AppColor.mainColor, size: 15,)),
                      ),
                      const SizedBox(width: 15,),
                      SmallText(text: "5.0"),
                      const SizedBox(width: 20),
                      SmallText(text: "209 comments")
                    ],
                  ),
                  const SizedBox(height: 15,),
                  Row(
                    children: [
                      IconAndText(icon: Icons.circle_sharp, text: "Normal", iconColor: AppColor.yellowColor,),
                      const SizedBox(width: 20,),
                      IconAndText(icon: Icons.location_on, text: "2.9km", iconColor: AppColor.mainColor,),
                      const SizedBox(width: 20,),
                      IconAndText(icon: Icons.access_time_rounded, text: "29 minute", iconColor: AppColor.iconColor2,),
                    ],
                  )
                ],
              ),
            ),
          ),
        ),
      ],
    );
  }
}
