import { Icon } from "@iconify/react";
import { SideNavItem } from "@/types";

export const SIDENAV_ITEMS: SideNavItem[] = [
    {
        title: "Home",
        path: "/",
        icon: <Icon icon="lucide:home" width="20" height="20"/>,
    },
    {
        title: "Players",
        path: "/players",
        icon: <Icon icon="iconamoon:profile" width="20" height="20" />,
    },
    {
        title: "Teams",
        path: "/teams",
        icon: <Icon icon="ri:team-line" width="20" height="20" />,
    },
    {
        title: "Standings",
        path: "/standings",
        icon: <Icon icon="ic:outline-leaderboard" width="20" height="20" />,
    },
    {
        title: "Games",
        path: "/games",
        icon: <Icon icon="ph:court-basketball-bold" width="20" height="20" />,
    },
    {
        title: "League Leaders",
        path: "/league-leaders",
        icon: <Icon icon="material-symbols:social-leaderboard-outline" width="20" height="20" />,
    },
    {
        title: "Contact",
        path: "/contact",
        icon: <Icon icon="wpf:message-outline" width="20" height="20" />,
    }
];
