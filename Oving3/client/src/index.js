import React, {Component} from 'react';
import { HashRouter, Route, NavLink } from 'react-router-dom';
import ReactDOM from 'react-dom';
import {Main} from "./main";





const root = document.getElementById('root');
if (root)
    ReactDOM.render(
    <HashRouter>
    <div>
    <Route exact path="/" component={Main} />
</div>
</HashRouter>,
root
);

