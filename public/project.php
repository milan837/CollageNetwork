<?php
use Psr\Http\Message\ServerRequestInterface as Request;
use Psr\Http\Message\ResponseInterface as Response;

require '../vendor/autoload.php';

$app = new \Slim\App;

$app->add(function ($req, $res, $next) {
    $response = $next($req, $res);
    return $response
            ->withHeader('Access-Control-Allow-Origin', 'http://mysite')
            ->withHeader('Access-Control-Allow-Headers', 'X-Requested-With, Content-Type, Accept, Origin, Authorization')
            ->withHeader('Access-Control-Allow-Methods', 'GET, POST, PUT, DELETE, PATCH, OPTIONS');
});

//project post
$app->post('/project/post', function (Request $request, Response $response, array $args) {
    
    require "db.php";

    $summary=$request->getParsedBody()['summary'];
    $usersLimit=$request->getParsedBody()['user_limit'];
    $userId=$request->getParsedBody()['user_id'];
    $date=date('Y-m-d');

    $query=$db->query("insert into project(user_id,summary,publish_on,user_limit) values('$userId','$summary','$date','$usersLimit')");

    if($query){
        return '{"status":"ok"}';
    }else{
        return '{"status":"failed"}';
    }

   
});

//project list
$app->post('/project/list',function(Request $request, Response $response, array $args){
    require "db.php";
    $paginationId=$request->getParsedBody()['pagination_id'];

    if($paginationId == "0"){
        $query=$db->query("select * from `project` order by project_id desc limit 5");
    }else{
        $query=$db->query("select * from `project` where project_id < $paginationId order by project_id desc limit 5");
    }

    $list=array();
    $results=$query->fetchAll(PDO::FETCH_OBJ);
    foreach($results as $result){

        $count=$db->query("select * from `project_participated` where project_id='".$result->project_id."'");
        $totalMembers=$count->rowCount();
        
        array_push($list,array(
            "project_id"=>$result->project_id,
            "summary"=>$result->summary,
            "user_id"=>$result->user_id,
            "total_members"=>$totalMembers,
            "publish_on"=>$result->publish_on
        ));
    }

    return json_encode(array("response"=>$list));
    
});

//participated project list
$app->post('/project/participated/list',function(Request $request, Response $response, array $args){
    
    require "db.php";
    $paginationId=$request->getParsedBody()['pagination_id'];
    $userId=$request->getParsedBody()['user_id'];

    if($paginationId == "0"){
        $query=$db->query("select * from `project`,`project_participated` where project_participated.user_id='$userId' and project.project_id=project_participated.project_id  order by project.project_id desc limit 5");
    }else{
        $query=$db->query("select * from `project`,`project_participated` where project_participated.user_id='$userId' and project.project_id=project_participated.project_id and project.project_id < paginationId order by project.project_id desc limit 5");
    }

    $list=array();
    $results=$query->fetchAll(PDO::FETCH_OBJ);
    foreach($results as $result){

        $count=$db->query("select * from `project_participated` where project_id='".$result->project_id."'");
        $totalMembers=$count->rowCount();
        
        array_push($list,array(
        	"p_p_id"=>$result->p_p_id,
            "project_id"=>$result->project_id,
            "summary"=>$result->summary,
            "user_id"=>$result->user_id,
            "total_members"=>$totalMembers,
            "publish_on"=>$result->publish_on
        ));
    }

    return json_encode(array("response"=>$list));
    
});

// project action of button participated
$app->post('/project/participated/button',function(Request $request,Response $response,array $args){
    require "db.php";
    $userId=$request->getParsedBody()['user_id'];
    $projectId=$request->getParsedBody()['project_id'];
    $date=date('Y-m-d');

    if(!empty($userId) && !empty($projectId)){
        $queryCheck=$db->query("select * from `project_participated` where user_id='$userId' and project_id='$projectId'");

        if($queryCheck->rowCount() == 0){
            $queryInsert=$db->query("insert into `project_participated`(project_id,user_id,participated_on) values('$projectId','$userId','$date')");
            if($queryInsert){
                $status="ok insert";
            }else{
                $status="sorry cannot participated";
            }
        }else{
            $queryExit=$db->query("delete from project_participated where project_id='$projectId' and user_id='$userId'");
            if($queryExit){
                $status="ok exit".$queryCheck->rowCount();
            }else{
                $status="sorry cannot exit from project";
            }

        }
    }

    echo json_encode(array("status"=>$status));

});

$app->run();