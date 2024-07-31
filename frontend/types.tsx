export type SideNavItems = {
    title: string;
    path: string;
    icon?: JSX.Element;
    submenu?: boolean;
    submenus?: SideNavItems[];
};
 
