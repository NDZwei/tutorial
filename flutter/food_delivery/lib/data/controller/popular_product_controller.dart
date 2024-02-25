
import 'package:food_delivery/data/repository/popular_product_repository.dart';
import 'package:get/get.dart';

class PopularProductController extends GetxController {
  final PopularProductRepository popularProductRepository;
  PopularProductController({required this.popularProductRepository});

  List<dynamic> _popularProductList = [];
  List<dynamic> get popularProductList => _popularProductList;
  Future<void> getPopularProductList() async {
    Response response = await popularProductRepository.getPopularProductList();
    if(response.statusCode == 200) {
      _popularProductList = [];
      // _popularProductList.addAll();
      update();
    } else {

    }
  }
}