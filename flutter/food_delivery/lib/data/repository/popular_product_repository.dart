
import 'package:food_delivery/data/controller/api_client.dart';
import 'package:get/get.dart';

class PopularProductRepository extends GetxService {
  final ApiClient apiClient;
  PopularProductRepository({required this.apiClient});

  Future<Response> getPopularProductList() async {
    return await apiClient.getData("https://www.dbestech.com/api/product/list");
  }
}