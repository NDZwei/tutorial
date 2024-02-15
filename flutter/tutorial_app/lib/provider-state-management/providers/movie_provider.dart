
import 'dart:math';

import 'package:flutter/cupertino.dart';

import '../models/movie.dart';

final List<Movie> initialData = List.generate(
    20, (index) => Movie(title: "Movie ${index+1}", duration: "${Random().nextInt(100) + 60} minutes")
);

class MovieProvider with ChangeNotifier {
  final List<Movie> _movies = initialData;
  List<Movie> get movies => _movies;

  final List<Movie> _favorites = [];
  List<Movie> get favorites => _favorites;

  void addToFavorite(Movie movie) {
    _favorites.add(movie);
    notifyListeners();
  }

  void removeFromFavorite(Movie movie) {
    _favorites.remove(movie);
    notifyListeners();
  }
}