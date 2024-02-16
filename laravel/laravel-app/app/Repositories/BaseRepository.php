<?php

namespace App\Repositories;

use Exception;
use Illuminate\Support\Facades\DB;

abstract class BaseRepository
{
    protected $model;

    public function __construct()
    {
        $this->model = $this->setModel();
    }

    abstract function getModel();

    public function setModel()
    {
        return app()->make($this->getModel());
    }

    public function getAll()
    {
        return $this->model->all();
    }

    public function getById($id)
    {
        return $this->model->where('id', $id)->first();
    }

    public function save(array $data) {
        if(isset($data['id']) && $data['id']) {
            $modelById = $this->model->find($data['id']);
            $modelById->update($data);
            return $modelById;
        } else {
            $result = $this->model->create($data);
            return $result;
        }
    }

    public function deleteById($id)
    {
        $data = $this->model->find($id);
        if($data) {
            $data->delete();
            return true;
        }
        return false;
    }

    public function getDataColumnWithRelation($columns = '*', $relation = [])
    {
        return $this->model->with($relation)->get($columns);
    }

    public function findByIds($columns = ['*'], array $ids)
    {
        return $this->model->whereIn('id', $ids)->get($columns);
    }

    public function findByColumns(array $conditions)
    {
        if(!empty($conditions)) {
            $data = $this->model->where($conditions);
        } else {
            $data = $this->model;
        }
        return $data->get();
    }

    public function getPage($pageIndex, $pageSize, $columns = ['*'], array $conditions) {
        if(!empty($conditions)) {
            $data = $this->model->where($conditions);
        } else {
            $data = $this->model;
        }
        $queryData = $data?->paginate($pageSize, $columns, $pageIndex);
        return $queryData;
    }
}
