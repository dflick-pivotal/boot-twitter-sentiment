	angular.module('report', ['ngResource', 'ui.bootstrap']).
	    factory('TweetData', function ($resource) {
	        return $resource('tweet_data');
	    }).
	    factory('TweetDataSearch', function ($resource) {
	        return $resource('tweet_data/search/tweetContains');
	    }).
	    controller('ReportController', function ($scope, TweetData, TweetDataSearch) {
	        function list() {
	            TweetData.get({page: $scope.pageNumber - 1, size: $scope.itemsPerPage}).$promise.then(function(pagedTweetData) {
	                $scope.pagedTweetData = pagedTweetData;
	            })
	        }

	        function search() {
	            TweetDataSearch.get({q: $scope.searchTweetData.tweetContains, page: $scope.pageNumber - 1, size: $scope.itemsPerPage}).$promise.then(function(pagedTweetData) {
	                $scope.pagedTweetData = pagedTweetData;
	            })
	        }

	        $scope.refresh = function() {
	            $scope.firstItemIndex = ($scope.itemsPerPage * ($scope.pageNumber - 1)) + 1;
	            $scope.lastItemIndex = Math.min(($scope.firstItemIndex + $scope.itemsPerPage) - 1, $scope.pagedTweetData.page.totalElements);

	            if ($scope.searchTweetData.tweetContains)
	                search();
	            else
	                list();
	        };

	        $scope.search = function() {
	            search();
	        };

	        $scope.clearSearch = function() {
	            $scope.searchTweetData.tweetContains = "";
	            list();
	        };

	        $scope.init = function() {
	            $scope.pageNumber = 1;
	            $scope.itemsPerPage = 10;
	            $scope.maxPageLinks = 15;
	            $scope.itemsPerPageOptions = [10, 15, 25, 50];
	            $scope.searchTweetData = {
	                tweetContains: ""
	            };
	            $scope.pagedTweetData = {
	                "contents": [],
	                "page": {
	                    "number": 0,
	                    "size": 1,
	                    "totalElements": 1,
	                    "totalPages": 1
	                }
	            };

	            $scope.refresh();
	        };
	    });
