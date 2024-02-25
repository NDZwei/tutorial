
import 'package:flutter/material.dart';
import 'package:food_delivery/utils/colors.dart';
import 'package:food_delivery/widgets/big_text.dart';
import 'package:food_delivery/widgets/expand_text.dart';

import '../../widgets/app_icon.dart';

class RecommendFoodDetail extends StatelessWidget {
  const RecommendFoodDetail({super.key});

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      backgroundColor: Colors.white,
      body: CustomScrollView(
        slivers: [
          SliverAppBar(
            toolbarHeight: 80,
            title: const Row(
              mainAxisAlignment: MainAxisAlignment.spaceBetween,
              children: [
                AppIcon(icon: Icons.clear),
                AppIcon(icon: Icons.shopping_cart),
              ],
            ),
            bottom: PreferredSize(
              preferredSize: Size.fromHeight(20), // space between app bar and container
              child: Container(
                child: Center(
                    child: BigText(text: "Bánh đa trộn", size: 24,)
                ),
                width: double.maxFinite,
                padding: const EdgeInsets.only(top: 5, bottom: 10),
                decoration: BoxDecoration(
                  color: Colors.white,
                  borderRadius: BorderRadius.only(
                    topRight: Radius.circular(20),
                    topLeft: Radius.circular(20)
                  )
                ),
              ),
            ),
            pinned: true,
            backgroundColor: AppColor.yellowColor,
            expandedHeight: 300,
            flexibleSpace: FlexibleSpaceBar(
              background: Image.asset(
                "assets/images/food0.png",
                width: double.maxFinite,
                fit: BoxFit.cover
              ),
            ),
          ),
          SliverToBoxAdapter(
            child: Column(
              children: [
                Container(
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
                      "hương vị ngon miệng hơn. Quán bánh đa trộn Ngõ Huyện đã trở thành "
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
                      "hương vị ngon miệng hơn.",),
                  margin: const EdgeInsets.only(left: 20, right: 20),
                )
              ],
            ) ,
          )
        ],
      ),
      bottomNavigationBar: Column(
        mainAxisSize: MainAxisSize.min,
        children: [
          Container(
            padding: const EdgeInsets.symmetric(vertical: 20, horizontal: 50),
            child: Row(
              mainAxisAlignment: MainAxisAlignment.spaceBetween,
              children: [
                AppIcon(backgroundColor: AppColor.mainColor, icon: Icons.remove, iconColor: Colors.white,),
                BigText(text: "35.000 VND/1", color: Colors.black54,),
                AppIcon(backgroundColor: AppColor.mainColor, icon: Icons.add, iconColor: Colors.white,),
              ],
            ),
          ),
          Container (
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
                  child: Icon(Icons.favorite, color: AppColor.mainColor,)
                ),
                Container(
                  padding: const EdgeInsets.all(15),
                  child: BigText(text: "35.000 | Thêm giỏ hàng", size: 20, color: Colors.white,),
                  decoration: BoxDecoration(
                    borderRadius: BorderRadius.circular(20),
                    color: AppColor.mainColor,
                  ),
                )
              ],
            ),
          ),
        ],
      ),
    );
  }
}
