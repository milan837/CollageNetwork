<?php
use Psr\Http\Message\ServerRequestInterface as Request;
use Psr\Http\Message\ResponseInterface as Response;

require '../vendor/autoload.php';

$app = new \Slim\App;

//notice list
$app->post('/notice/list', function (Request $request, Response $response, array $args) {
    
    require "db.php";

    $paginationId=$request->getParsedBody()['pagination_id'];
    $type=$request->getParsedBody()['type'];

    if($paginationId == "0"){
    	$query=$db->query("select * from `notice_board` where notice_type='$type' order by notice_board_id desc limit 5");
    }else{
    	$query=$db->query("select * from `notice_board` where notice_type='$type' and notice_board_id < $paginationId order by notice_board_id desc limit 5");
    }

    $list=array();

    $results=$query->fetchAll(PDO::FETCH_OBJ);
    foreach($results as $result){
    	array_push($list,array(
    		"notice_board_id"=>$result->notice_board_id,
    		"admin_id"=>$result->admin_id,
    		"subject"=>$result->subject,
    		"body"=>$result->body,
    		"image_url"=>$result->image_url,
    		"notice_type"=>$result->notice_type,
    		"publish_on"=>$result->publish_on
    	));
    }

    return json_encode(array("response"=>$list));
});

//notice destails
$app->get('/notice/profile/{id}',function(Request $request,Response $result,array $args){
	require "db.php";
	$id=$args['id'];

	$query=$db->query("select * from `notice_board` where notice_board_id='$id'");
	
	$result=$query->fetch();
	$list=array(
    		"notice_board_id"=>$result['notice_board_id'],
    		"admin_id"=>$result['admin_id'],
    		"subject"=>$result['subject'],
    		"body"=>$result['body'],
    		"image_url"=>$result['image_url'],
    		"notice_type"=>$result['notice_type'],
    		"publish_on"=>$result['publish_on']
    	);

	return json_encode(array("response"=>$list));
});

// notice publish
$app->post('/notice/post',function(Request $request,Response $response,array $args){
	require "db.php";
	$subject=$request->getParsedBody()['subject'];
	$body=$request->getParsedBody()['body'];
	$type=$request->getParsedBody()['type'];
	$date=date('Y-m-d');
	$adminId=$request->getParsedBody()['admin_id'];

	$query=$db->query("insert into `notice_board`(admin_id,subject,body,notice_type,publish_on) values('$adminId','$subject','$body','$type','$date')");

	if($query){
		return '{"status":"ok"}';
	}else{
		return '{"status":"faield"}';
	}
});
$app->run();