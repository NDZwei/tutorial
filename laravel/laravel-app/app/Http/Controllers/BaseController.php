<?php

namespace App\Http\Controllers;

use Exception;
use Illuminate\Database\Eloquent\Model;
use Illuminate\Http\Request;
use Illuminate\Http\Response;
use Illuminate\Support\Facades\DB;

class BaseController extends Controller {
    protected $service;

    public function __construct($service) {
        $this->service = $service;
    }

    public function getResponse200($data = [], $message = 'Success') {
        $response = [
            'status' => Response::HTTP_OK,
            'message' => $message,
            'data' => $data,
        ];
        return response()->json($response);
    }

    public function getResponse400($message = 'Bad request') {
        $response = [
            'status' => Response::HTTP_BAD_REQUEST,
            'message' => $message,
            'data' => null,
        ];
        return response()->json($response);
    }

    public function getResponse404($message = 'Item not found') {
        $response = [
            'status' => Response::HTTP_NOT_FOUND,
            'message' => $message,
            'data' => null,
        ];
        return response()->json($response);
    }

    public function getResponse500($message = 'Error') {
        $response = [
            'status' => Response::HTTP_INTERNAL_SERVER_ERROR,
            'message' => $message,
            'data' => null,
        ];
        return response()->json($response);
    }

    public function getAll() {
        $data = $this->service->getAll();
        return $this->getResponse200($data);
    }

    public function getById($id) {
        try {
            $data = $this->service->getById($id);
            return $this->getResponse200($data);
        } catch (Exception $e) {
            return $this->getResponse404();
        }
    }

    public function save(Request $request) {
        $data = $request->all();
        $result = $this->service->save($data);
        if($result instanceof Model) {
            return $this->getResponse200($result);
        } else {
            return $this->getResponse500($result);
        }
    }

    public function deleteById($id) {
        try {
            DB::beginTransaction();
            $result = $this->service->deleteById($id);
            DB::commit();
            return response()->json($result);
        } catch(Exception $e) {
            DB::rollBack();
            return $this->getResponse500($e->getMessage());
        }
    }

    public function getDataColumnWithRelation(Request $request) {
        $data = $request->all();
        $result = $this->service->getDataColumnWithRelation($data['columns'] ?? ['*'], $data['relation'] ?? []);
        return $this->getResponse200($result);
    }

    public function findByIds(Request $request) {
        $data = $request->all();
        $result = $this->service->findByIds($data['columns'] ?? ['*'], $data['ids']);
        return $this->getResponse200($result);
    }

    public function findByColumns(Request $request) {
        $data = $request->all();
        $result = $this->service->findByColumns($data['conditions'] ?? []);
        return $this->getResponse200($result);
    }

    public function getPage(Request $request) {
        $data = $request->all();
        $pageIndex = isset($data['page_index']) ? $data['page_index'] : 1;
        $pageSize = isset($data['page_size']) ? $data['page_size'] : 10;
        $result = $this->service->getPage($pageIndex, $pageSize, $data['columns'] ?? ['*'], $data['conditions'] ?? []);
        return response()->json($result);
    }
}
