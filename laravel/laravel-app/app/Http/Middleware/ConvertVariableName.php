<?php

namespace App\Http\Middleware;

use Closure;
use Illuminate\Http\JsonResponse;
use Illuminate\Http\Request;
use Illuminate\Support\Str;

class ConvertVariableName {
    public const CASE_SNAKE = 'snake';
    public const CASE_CAMEL = 'camel';

    public function handle(Request $request, Closure $next)
    {
        $request->replace(
            $this->convertToCase(
                self::CASE_SNAKE,
                $request->post()
            )
        );
        $response = $next($request);
        if ($response instanceof JsonResponse) {
            $response->setData(
                $this->convertToCase(
                    self::CASE_CAMEL,
                    json_decode($response->getContent(), true)
                )
            );
        }
        return $response;
    }

    private function convertToCase(string $case, $data)
    {
        if (!is_array($data)) {
            return $data;
        }
        $array = [];
        foreach ($data as $key => $value) {
            $array[Str::{$case}($key)] = is_array($value)
                ? $this->convertToCase($case, $value)
                : $value;
        }
        return $array;
    }
}
