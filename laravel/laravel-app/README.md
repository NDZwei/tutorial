Before starting laravel-app required `cd laravel-app` and change config in `laravel-app\.env`
In cmd run:
    - `composer update`
    - `composer install`
    - `php artisan migrate`
    - `php artisan key:generate`
    - `php artisan db:seed`
    - `php artisan storage:link`


Other with cmd:
    - make model and migration: `php artisan make:model NameModel -m` 
    - make request: `php artisan make:request NameRequest`
