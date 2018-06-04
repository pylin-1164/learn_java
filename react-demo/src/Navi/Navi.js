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
    state = {
        collapsed: false,
        mode: 'inline',
    };

    toggle = () => {
        this.setState({
            collapsed: !this.state.collapsed,
        });
    };
    
    handleClick = () =>{
       alert(123);
    };
    
    render() {
    	
        return (
            <Layout>
                <Sider
                    trigger={null}
                    collapsible
                    collapsed={this.state.collapsed}
                >
                    <div className="logo" />
                    <Menu theme="dark" mode="inline" defaultSelectedKeys={['1']}>
                        <Menu.Item key="1">
                            <Icon type="user" />
                            <span className="nav-text">nav 1</span>
                        </Menu.Item>
                        <Menu.Item key="2">
                            <Icon type="video-camera" />
                            <span className="nav-text">nav 2</span>
                        </Menu.Item>
                        <Menu.Item key="3">
                            <Icon type="upload" />
                            <span className="nav-text">nav 3</span>
                        </Menu.Item>
                    </Menu>
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
		                        <Menu.Item key="1" onClick={this.handleClick} >
		                            <Icon type="user"  />
		                            <span className="nav-text">用户管理</span>
		                        </Menu.Item>
		                        <Menu.Item key="2" onClick={this.handleClick}>
		                            <Icon type="video-camera"/>
		                            <span className="nav-text">角色管理</span>
		                        </Menu.Item>
		                        <Menu.Item key="3" onClick={this.handleClick}>
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
                        <div style={{ padding: 24, background: '#fff', minHeight: 610 }}>

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