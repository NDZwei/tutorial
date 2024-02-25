import 'package:flutter/material.dart';
import 'package:food_delivery/utils/colors.dart';
import 'package:food_delivery/widgets/AppColumn.dart';
import 'package:food_delivery/widgets/app_icon.dart';
import 'package:food_delivery/widgets/expand_text.dart';

import '../../widgets/big_text.dart';

class PopularFoodDetail extends StatelessWidget {
  const PopularFoodDetail({super.key});

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      backgroundColor: Colors.white,
      body: Stack(
        children: [
          // image
          Positioned(
              left: 0,
              right: 0,
              child: Container(
                width: double.maxFinite,
                height: 320,
                decoration: const BoxDecoration(
                    image: DecorationImage(
                  fit: BoxFit.cover,
                  image: AssetImage("assets/images/food0.png"),
                )),
              )),
          // icon
          const Positioned(
            top: 35,
            left: 20,
            right: 20,
            child: Row(
              mainAxisAlignment: MainAxisAlignment.spaceBetween,
              children: [
                AppIcon(icon: Icons.arrow_back_ios_new),
                AppIcon(icon: Icons.shopping_cart),
              ],
            )
          ),
          // food detail
          Positioned(
            left: 0,
            right: 0,
            bottom: 0,
            top: 300,
            child: Container(
              padding: const EdgeInsets.only(left: 20, right: 20, top: 20),
              decoration: const BoxDecoration(
                borderRadius: BorderRadius.only(
                  topLeft: Radius.circular(20),
                  topRight: Radius.circular(20)
                ),
                color: Colors.white,
              ),
              child: Column(
                crossAxisAlignment: CrossAxisAlignment.start,
                children: [
                  const AppColumn(text: "Bánh đa trộn"),
                  const SizedBox(height: 20,),
                  BigText(text: "Mô tả"),
                  const SizedBox(height: 15,),
                  Expanded(
                    child: SingleChildScrollView(
                      child: ExpandText(text: "Quán bánh đa trộn Ngõ Huyện đã trở thành "
                          "một cái tên quen thuộc trong lòng người dân Hà Nội. Dù chỉ là "
                          "gánh hàng rong với vài chiếc ghế nhựa nhưng luôn tấp nập thực khách. "
                          "Một bát bánh đa trộn tại quán gồm có bánh đa mềm, giò, chả, hành khô, "
                          "rau muống hoặc rau cần. Điểm hấp dẫn nhất của quán chính là sự đa dạng "
                          "của topping, với giò lụa, giò tai, giò chả và chả cá. Trong đó, "
                          "giò được làm tại chỗ và sử dụng hết trong ngày, đảm bảo tươi ngon và ngọt "
                          "thanh vị thịt hơn nữa, với sự nhiệt tình và thân thiện của "
                          "chủ quán tạo nên không gian ấm cúng, khiến thực khách cảm thấy thêm "
                          "hương vị ngon miệng hơn. Quán bánh đa trộn Ngõ Huyện đã trở thành "
                          "một cái tên quen thuộc trong lòng người dân Hà Nội. Dù chỉ là "
                          "gánh hàng rong với vài chiếc ghế nhựa nhưng luôn tấp nập thực khách. "
                          "Một bát bánh đa trộn tại quán gồm có bánh đa mềm, giò, chả, hành khô, "
                          "rau muống hoặc rau cần. Điểm hấp dẫn nhất của quán chính là sự đa dạng "
                          "của topping, với giò lụa, giò tai, giò chả và chả cá. Trong đó, "
                          "giò được làm tại chỗ và sử dụng hết trong ngày, đảm bảo tươi ngon và ngọt "
                          "thanh vị thịt hơn nữa, với sự nhiệt tình và thân thiện của "
                          "chủ quán tạo nên không gian ấm cúng, khiến thực khách cảm thấy thêm "
                          "hương vị ngon miệng hơn."),
                    ),
                  )
                ],
              )
            )
          ),
        ],
      ),
      bottomNavigationBar: Container (
        height: 100,
        padding: const EdgeInsets.symmetric(vertical: 20, horizontal: 20),
        decoration: BoxDecoration(
          color: AppColor.buttonColor,
          borderRadius: const BorderRadius.only(
            topLeft: Radius.circular(40),
            topRight: Radius.circular(40)
          ),
        ),
        child: Row(
          mainAxisAlignment: MainAxisAlignment.spaceBetween,
          children: [
            Container(
              padding: const EdgeInsets.all(15),
              decoration: BoxDecoration(
                borderRadius: BorderRadius.circular(20),
                color: Colors.white,
              ),
              child: Row(
                children: [
                  Icon(Icons.remove, color: AppColor.signColor),
                  const SizedBox(width: 10,),
                  BigText(text: "0"),
                  const SizedBox(width: 10,),
                  Icon(Icons.add, color: AppColor.signColor,)
                ],
              ),
            ),
            Container(
              padding: const EdgeInsets.all(15),
              child: BigText(text: "Thêm giỏ hàng", size: 18, color: Colors.white,),
              decoration: BoxDecoration(
                borderRadius: BorderRadius.circular(20),
                color: AppColor.mainColor,
              ),
            )
          ],
        ),
      ),
    );
  }
}
