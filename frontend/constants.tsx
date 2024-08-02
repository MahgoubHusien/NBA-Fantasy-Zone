import { Icon } from "@iconify/react"
import { SideNavItem } from "./types"

export const SIDENAV_ITEMS: SideNavItem[] = [
    {
        title: "Home",
        path: "/",
        icon: <Icon icon="lucide:home" width = "24" height = "24" />,
    },
    {
        title: "Players",
        path: "/players",
        icon: <Icon icon="carbon:user-avatar-filled" width = "24" height = "24" />,
    },
    {
        title: "Teams",
        path: "/teams",
        icon: <Icon icon="carbon:group-filled" width = "24" height = "24" />,
    },
    {
        title: "Standings",
        path: "/standings", 
        icon: <Icon icon="carbon:leaderboard-filled" width = "24" height = "24" />,
    },
    {
        title: "Games",
        path: "/games",
        icon: <Icon icon="carbon:live-stream-filled" width = "24" height = "24" />,
    },
    {
        title: "League Leaders",
        path: "/league-leaders",
        icon: <Icon icon="carbon:medal-filled" width = "24" height = "24" />,
    },
    {
        title: "Contact",
        path: "/contact",
        icon: <Icon icon="carbon:email-filled" width = "24" height = "24" />,
    }

]