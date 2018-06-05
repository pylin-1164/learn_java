/**
 * http://usejsdoc.org/
 */
import { Layout, Menu, Breadcrumb, Icon } from 'antd';
import React, { Component } from 'react';
import 'antd/dist/antd.css';
import logo from '../logo.svg';
import './Navi.css'
const { Header, Content, Footer, Sider } = Layout;

class SiderDemo extends Component {
	constructor(props) {
		super(props);
		this.state = {
				date: '',
				menus:[{"name":"组织管理","url":"http://localhost:3000/org","id":"1"},{"name":"用户管理","url":"http://localhost:3000/user","id":"2"}]
		};
	};
	
	
    state = {
        collapsed: false,
        mode: 'inline',
        menus:[{"name":"组织管理","url":"http://localhost:3000/org","id":"1"},{"name":"用户管理","url":"http://localhost:3000/user","id":"2"}]
    };
    

    toggle = () => {
        this.setState({
            collapsed: !this.state.collapsed
        });
    };
    
    handleClick(item,event){
    	var menuItems = {"users":[{"name":"组织管理","url":"http://localhost:3000/org","id":"1"},{"name":"用户管理","url":"http://localhost:3000/user","id":"2"}],
    			"org":[{"name":"电源管理","url":"http://localhost:3000/sys1","id":"3"},{"name":"IP管理","url":"http://locahost:3000/ip","id":"4"}],
    			"sys":[{"name":"电源管理2","url":"http://localhost:3000/sys1","id":"5"},{"name":"IP管理2","url":"http://locahost:3000/ip","id":"6"}]
    	};
    	this.setState({
    		menus: menuItems[item]
        });
    }
    
    render() {
    	
        return (
            <Layout>
                <Sider
                    trigger={null}
                    collapsible
                    collapsed={this.state.collapsed}
                >
                    <div className="logo" />
                    <div id="leftMenus">
	                    <Menu theme="dark" mode="inline" defaultSelectedKeys={['1']}>
	                        {
	                        	this.state.menus.map(function(item){
	                        		return <Menu.Item key={item.id}>
		                        		<Icon type="user" />
		                        		<span className="nav-text">{item.name}</span>
		                        	</Menu.Item>
		                        })
	                        }
		                </Menu>
                    </div>
                </Sider>
                <Layout>
                    <Header style={{ background: '#000', padding: 0 ,height:68,minWidth:880}}>
			             <div style={{float:'left'}}>
				             <span style={{color:'#fff', paddingLeft:'2%', fontSize:'1.4em'}}>
		                        <Icon
		                            className="trigger"
		                            type={this.state.collapsed ? 'menu-unfold' : 'menu-fold'}
		                            onClick={this.toggle}
		                            style={{cursor: 'pointer'}}
		                        />
	                         </span>
			             </div>
			             <div style={{float:'left',marginLeft:'25px'}}>
				             <Menu theme="dark" mode="horizontal" style={{lineHeight:'68px'}} defaultSelectedKeys={['1']}>
		                        <Menu.Item key="1" onClick={this.handleClick.bind(this,'users')} >
		                            <Icon type="user"  />
		                            <span className="nav-text">用户管理</span>
		                        </Menu.Item>
		                        <Menu.Item key="2" onClick={this.handleClick.bind(this,'org')}>
		                            <Icon type="video-camera"/>
		                            <span className="nav-text">角色管理</span>
		                        </Menu.Item>
		                        <Menu.Item key="3" onClick={this.handleClick.bind(this,'sys')}>
		                            <Icon type="upload" />
		                            <span className="nav-text">系统管理</span>
		                        </Menu.Item>
		                        </Menu>
			             </div>
	                     <span style={{color:'#fff', float:'right', paddingRight:'1%'}}>
                            <img src={logo} className="App-logo" alt="logo" />
                         </span>
                    </Header>
                    <Content style={{ margin: '0 16px' }}>
                        <Breadcrumb style={{ margin: '12px 0' }}>
                            <Breadcrumb.Item>User</Breadcrumb.Item>
                            <Breadcrumb.Item>Bill</Breadcrumb.Item>
                        </Breadcrumb>
                        <div style={{ padding: 24, background: '#fff', minHeight: 600 }}>

                        </div>
                    </Content>
                    <Footer style={{ textAlign: 'center',height:12 }}>
                        Ant Design ©2016 Created by Ant UED
                    </Footer>
                </Layout>
            </Layout>
        );
    }
}


export default SiderDemo;