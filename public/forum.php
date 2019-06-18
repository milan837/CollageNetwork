<?php
use Psr\Http\Message\ServerRequestInterface as Request;
use Psr\Http\Message\ResponseInterface as Response;

require '../vendor/autoload.php';

$app = new \Slim\App;

//forum question ask
$app->post('/forum/question/ask', function (Request $request, Response $response, array $args) {
	require "db.php";

	$question=$request->getParsedBody()['question'];
	$userId=$request->getParsedBody()['user_id'];
	$date=date('Y-m-d');

	if(!empty($question) && !empty($userId)){
		$queryAskQuestion=$db->query("insert into `forum_question`(user_id,question,publish_on) values('$userId','$question','$date')");
		if($question){
			$status="ok";
		}else{
			$status="error";
		}
	}

	return json_encode(array("status"=>$status));

});

//forum question ask
$app->post('/forum/answer/post', function (Request $request, Response $response, array $args) {
	require "db.php";

	$answer=$request->getParsedBody()['answer'];
	$userId=$request->getParsedBody()['user_id'];
	$fQId=$request->getParsedBody()['f_q_id'];
	$date=date('Y-m-d');

	if(!empty($answer) && !empty($userId) && !empty($fQId)){
		$queryAskQuestion=$db->query("insert into `forum_answer`(f_q_id,user_id,answer,publish_on) values('$fQId','$userId','$answer','$date')");
		if($queryAskQuestion){
			$status="ok";
		}else{
			$status="error";
		}
	}

	return json_encode(array("status"=>$status));

});


//forum question list
$app->post('/forum/question/list', function (Request $request, Response $response, array $args) {
	require "db.php";
	require "class/UserLogin.php";

	$paginationId=$request->getParsedBody()['pagination_id'];

	if($paginationId == '0'){
		$query=$db->query("select * from `forum_question` order by f_q_id desc limit 5");
	}else{
		$query=$db->query("select * from `forum_question` where f_q_id < $paginationId order by f_q_id desc limit 5");
	}
	$list=array();

	$results=$query->fetchAll(PDO::FETCH_OBJ);
	foreach($results as $result){
		$user=new User($db,$result->user_id);
		array_push($list,array(
			"f_q_id"=>$result->f_q_id,
			"user_id"=>$result->user_id,
			"username"=>$user->getUsername(),
			"question"=>$result->question,
			"publish_on"=>$result->publish_on
		));
	}

	return json_encode(array("response"=>$list));

});

//forum answer list
$app->post('/forum/answer/list', function (Request $request, Response $response, array $args) {
	require "db.php";
	require "class/UserLogin.php";

	$paginationId=$request->getParsedBody()['pagination_id'];
	$fQId=$request->getParsedBody()['f_q_id'];

	if($paginationId == '0'){
		$query=$db->query("select * from `forum_answer` where f_q_id='$fQId' order by f_a_id desc limit 5");
	}else{
		$query=$db->query("select * from `forum_answer` where f_q_id='$fQId' and f_a_id < $paginationId order by f_a_id desc limit 5");
	}
	$list=array();

	$results=$query->fetchAll(PDO::FETCH_OBJ);
	foreach($results as $result){
		$user=new User($db,$result->user_id);
		array_push($list,array(
			"f_a_id"=>$result->f_a_id,
			"f_q_id"=>$result->f_q_id,
			"username"=>$user->getUsername(),
			"user_id"=>$result->user_id,
			"answer"=>$result->answer,
			"publish_on"=>$result->publish_on
		));
	}

	return json_encode(array("response"=>$list));

});

$app->run();