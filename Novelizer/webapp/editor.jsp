<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>Novelize Editor</title>
    
    <link rel="stylesheet" type="text/css" href="css/editor.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.4/jquery.min.js"></script>
    <script src="https://ajax.googleapis.com/ajax/libs/jqueryui/1.11.4/jquery-ui.min.js"></script>
    <script src="js/handlebars-v4.0.5.js"></script>
</head>
<body>
        <navigation>
            <ul>
                <li class="tab-edit"><a href="#none;">편집</a></li>
                <li class="editor-save">저장하기</li>
                <li class="editor-save"><a href="/viewer.jsp?sceneId=${param.sceneId}">감상하기</a></li>
            </ul>
            <!-- <button class="editor-save">저장하기</button> -->
        </navigation>
        <main>
            <div id="editor">
                <div class="layout-left">
                    <!--start preview-->
                    <div id="preview"> 
                        <div id="screen">
                            <div id="layer-background" class="layer"></div>
                            <div id="layer-object" class="layer">
                                <div class="character-left"></div>
                                <div class="character-right"></div>
                            </div>
                            <div id="layer-text" class="layer">
                                <select class="preview-select-character">
                                    <option value="0">독백</option>
                                    <option value="1">영희</option>
                                    <option value="2">철수</option>
                                </select>
                                <textarea class="preview-text-box" placeholder="이곳에 대사를 입력해주세요" maxlength="300"></textarea>
                            </div>
                        </div>
                    </div>
                    <!--//end preview-->

                    <!--start toolbox-->
                    <div id="toolbox">
                        <ul class="tab-menu">
                            <li class="tab-background">배경</li>
                            <li class="tab-character">캐릭터</li>
                        </ul>
                        <div class="container">
                            <div id="toolbox-background" class="toolbox-tab">
                                <ul class="background-item-list">
                                <!--start template-->
                                <script id="background-preset-template" type="text/x-handlebars-template">
                                    {{#each presetList}}
                                    <li class="background-item toolbox-item" data-image="{{image}}" data-preset-id="{{presetId}}">
                                        <img src="{{image}}" />
                                        <h4>{{name}}</h4>
                                    </li>
                                    {{/each}}
                                </script>
                                <!--//end template-->
                                </ul>
                            </div>
                            <div id="toolbox-character" class="toolbox-tab">
                                <ul class="character-item-list">
                                <!--start template-->
                                <script id="character-preset-template" type="text/x-handlebars-template">
                                    {{#each characterList}}
                                    <li class="character-item toolbox-item" data-image="{{image}}" data-character-id="{{characterId}}">
                                        <img src="{{image}}" />
                                        <h4>{{name}}</h4>
                                    </li>
                                    {{/each}}
                                </script>
                                <!--//end template-->
                                </ul>
                            </div>
                            <div id="toolbox-deactivate">
                                <p>먼저 타임라인에서 +버튼을 선택해 블록을 선택하세요</p><br />
                                <p>추간된 블록의 왼쪽색인을 이용해 순서를 바꿀 수 있습니다.</p>
                            </div>
                        </div>
                    </div>
                    <!--//end toolbox-->
                </div>
                <div class="layout-right">
                    <!--start timeline-->
                    <div id="timeline"> 
                        <h1>Timeline</h1>
                        <div>
                            <button class="button-add-block">+</button>
                        </div>
                        <ol id="block-list">
                        <!--start template-->
                        <script id="timeline-template" type="text/x-handlebars-template">
                            {{#each blockList}}
                            <li class="block" data-block-id="{{blockId}}">
                                <div class="handler">
                                </div>
                                <div class="container">
                                    <ul class="action-list">
                                        {{#each actionList}}
                                        <li class="action {{actionType}}" data-action-id="{{actionId}}">
                                            {{#equals actionType "text"}}
                                                <select class="character-select">
                                                    <option value="0" {{#equals characterId 0}}selected{{/equals}}>독백</option>
                                                    <option value="1" {{#equals characterId 1}}selected{{/equals}}>영희</option>
                                                    <option value="2" {{#equals characterId 2}}selected{{/equals}}>철수</option>
                                                </select>
                                                <textarea class="editable action-text" placeholder="대사를 입력하세요">{{text}}</textarea>
                                            {{/equals}}

                                            {{#equals actionType "background"}}
                                                배경
                                                <select class="preset-select">
                                                    <option value="0" {{#equals presetId 1}}selected{{/equals}}>없음</option>
                                                    <option value="1" {{#equals presetId 1}}selected{{/equals}}>하늘</option>
                                                    <option value="2" {{#equals presetId 2}}selected{{/equals}}>공원</option>
                                                </select>
                                                <span class="button-remove-action">x</span>
                                            {{/equals}}

                                            {{#equals actionType "character"}}
                                                캐릭터
                                                <select class="character-select">
                                                    <option value="1" {{#equals characterId 1}}selected{{/equals}}>영희</option>
                                                    <option value="2" {{#equals characterId 2}}selected{{/equals}}>철수</option>
                                                </select>
                                                <select class="option-select">
                                                    <option value="0" {{#equals optionId 0}}selected{{/equals}}>등장</option>
                                                    <option value="1" {{#equals optionId 1}}selected{{/equals}}>퇴장</option>
                                                </select>
                                                x:<input type="text" class="character-pos-x" size="2" value="{{posX}}" />
                                                y:<input type="text" class="character-pos-y" size="2" value="{{posY}}" />
                                                <span class="button-remove-action">x</span>
                                            {{/equals}}
                                        </li>
                                        {{/each}}
                                    </ul>
                                    <div class="block-menu-box">
                                        <button class="button-add-action">+</button>
                                        <ul class="action-type-list">
                                            <li class="add-character-action">캐릭터</li>
                                            <li class="add-background-action">배경</li>
                                        </ul>
                                        <button class="button-remove-block">x</button>
                                    </div>
                                </div>
                            </li>
                            {{/each}}
                        </script>
                        <!--//end template-->
                        </ol>
                        
                    </div> 
                    <!--//end timeline-->
                </div>
            </div>
        </main>

    <script type="text/javascript" src="js/dataSync.js"></script>
    <script type="text/javascript" src="js/editor.js"></script>
    <script type="text/javascript">
	EditorDataSync.sceneId = ${param.sceneId};
        
    </script>
</body>
</html>